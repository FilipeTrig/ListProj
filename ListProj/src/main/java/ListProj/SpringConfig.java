package ListProj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import ListProj.data.EntryClientService;
import ListProj.data.EntryDataAcessInterface;
import ListProj.data.EntryDataService;
import ListProj.services.EntryBussinessService;
import ListProj.services.EntryBussinessServiceInterface;


@Configuration
public class SpringConfig {
    
    /*@Bean(name = "ordersBussinessService", initMethod = "init", destroyMethod = "destroy")
    @RequestScope //@SessionScope
    public OrdersBussinessServiceInterface getOrdersBussinessService() {
        return new OrdersBussinessService();
    }*/

    @Bean(name = "entryBussinessService", initMethod = "init", destroyMethod = "destroy")
    @RequestScope //@SessionScope
    public EntryBussinessServiceInterface getEntryBussinessService() {
        return new EntryBussinessService();
    }

    @Bean(name = "EntryDB")
    @RequestScope //@SessionScope
    public EntryDataAcessInterface getDataService() {
        return new EntryDataService();
    }
}
