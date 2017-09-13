package pm.mbo.spring.database.mongo1;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import pm.mbo.spring.database.mongo1.repo.UserMongo1Repository;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(
        basePackageClasses = {UserMongo1Repository.class}
)
public class Mongo1Config {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.mongo1")
    public MongoProperties mongo1Properties() {
        return new MongoProperties();
    }

    @Bean
    @Primary
    public MongoClient mongo(final ObjectProvider<MongoClientOptions> options, final Environment environment) throws UnknownHostException {
        return mongo1Properties().createMongoClient(options.getIfAvailable(), environment);
    }

}
