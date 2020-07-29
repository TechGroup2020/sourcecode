package com.perfumaa.rest.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

@Component
@ConditionalOnProperty(name= {"mongo.ssl.enabled"}, havingValue="true")
public class MongoSSLConnection extends AbstractMongoConfiguration {
	
	private static final Logger LOG = LogManager.getLogger(MongoSSLConnection.class);
	
	@Value(value = "${spring.data.mongodb.host}")
	String mongohost;
	
	@Value(value = "${spring.data.mongodb.database}")
	String mongodatabaseName;
	
	@Value(value = "${spring.data.mongodb.port}")
	int port;
	
	@Value(value = "${spring.data.mongodb.uri}")
	String uri;
	
	@Value(value = "${mongo.db.key-store}")
	String keystore;

	@Value(value = "${mongo.db.key-store-password}")
	String keystorepassword;
	
	@Value(value = "${mongo.db.key-store-type}")
	String keystoretype;

	@Value(value = "${mongo.db.ssl-version}")
	String sslversion;
	
	@Value(value = "${mongo.db.key-algorithm}")
	String keyalgorithm;

	
	@Override
	public MongoClient mongoClient() {
		
      
		 try {
			return getSecureMongoClient();
			
		} catch (UnrecoverableKeyException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException
				| CertificateException | IOException e) {
			LOG.error("Exception in Mongo SSL Connection Keystore" );
			e.printStackTrace();
		}
		 return null;
			 }
	
	private MongoClient getSecureMongoClient() throws NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException, UnrecoverableKeyException, KeyManagementException
	{
		try
		{
		    SSLContext sslcontext = SSLContext.getInstance(sslversion);
		    KeyManagerFactory kmf =  KeyManagerFactory.getInstance(keyalgorithm);
		    KeyStore ks = KeyStore.getInstance(keystoretype);
		    
		    InputStream stream= this.getClass().getResourceAsStream(keystore);
		    
		    ks.load(stream, keystorepassword.toCharArray());
		    
		    kmf.init(ks, keystorepassword.toCharArray());

		     TrustManagerFactory tmf = TrustManagerFactory
		            .getInstance(TrustManagerFactory.getDefaultAlgorithm());
		    tmf.init(ks);
		    TrustManager[] tm = tmf.getTrustManagers();

		    sslcontext.init(kmf.getKeyManagers(), tm, null);
		    //SSLSocketFactory sslSocketFactory = sslcontext.getSocketFactory();
		    
		    MongoClientOptions options = MongoClientOptions.builder().sslEnabled(true).sslContext(sslcontext).build();
		    
		    if(stream != null)
		    {
		    	stream.close();
		    }
		    return new MongoClient(mongohost,options);
		}
		catch(Exception e)
		{
			throw e;
			
		}
	}
	
	
	@Override
	protected String getDatabaseName() {
		return mongodatabaseName;
	}
	
	

}
