package org.pti.poster.dao;


import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.pti.poster.dao.repository.RepositoryPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@PropertySource("application.properties")
@EnableMongoRepositories(basePackageClasses = RepositoryPackage.class)
@ComponentScan
@Profile("test")
public class TestMongoConfiguration extends AbstractMongoConfiguration {

    @Autowired private Environment env;
    
    @Override
    protected String getDatabaseName() {
        return env.getRequiredProperty("mongo.db.name");
    }

    @Override
    public Mongo mongo() throws Exception {
        Integer port = Constants.DATABASE.PORT + 100;
        MongodExecutable mongodExe = MongodStarter.getDefaultInstance().prepare(new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()))
                .build());
        mongodExe.start();
        return new MongoClient(Constants.DATABASE.HOST, port);
    }
}
