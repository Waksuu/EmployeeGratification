module pl.kacper.starzynski.employeeGratification.achievementCard {
    exports pl.kacper.starzynski.employeeGratification.achievementCard.domain.externalPorts to
            pl.kacper.starzynski.employeeGratification.achievementConfiguration;
    requires lombok;
    requires spring.tx;
    requires spring.data.mongodb;
    requires spring.web;
    requires org.mongodb.driver.core;
    requires spring.data.commons;
    requires pl.kacper.starzynski.employeeGratification.sharedKernel;
    requires spring.context;
    requires spring.core;
    requires pl.kacper.starzynski.employeeGratification.achievementConfigurationApi;
}