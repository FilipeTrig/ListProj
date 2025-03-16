package ListProj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import ListProj.data.EntryClientService;
import ListProj.data.EntryDataAcessInterface;
import ListProj.data.EntryDataService;
import ListProj.data.PersonalClientService;
import ListProj.data.PersonalDataAcessInterface;
import ListProj.services.EntryBussinessService;
import ListProj.services.EntryBussinessServiceInterface;
import ListProj.services.PersonalBussinessService;
import ListProj.services.PersonalBussinessServiceInterface;


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

    @Bean(name = "personalBussinessService", initMethod = "init", destroyMethod = "destroy")
    @RequestScope //@SessionScope
    public PersonalBussinessServiceInterface PersonalBussinessServiceInterface() {
        return new PersonalBussinessService();
    }

    @Bean(name = "EntryDB")
    @RequestScope //@SessionScope
    public EntryDataAcessInterface getDataService() {
        return new EntryClientService();
    }

    @Bean(name = "PersonalDB")
    @RequestScope //@SessionScope
    public PersonalDataAcessInterface getPersonalService() {
        return new PersonalClientService();
    }
}
