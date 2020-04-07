module pl.kacper.starzynski.employeeGratification.achievementCard {
    exports pl.kacper.starzynski.employeeGratification.achievementCard.domain.ports to
            pl.kacper.starzynski.employeeGratification.achievementConfiguration;
    requires lombok;
    requires spring.tx;
    requires spring.data.mongodb;
    requires spring.web;
    requires org.mongodb.driver.core;
    requires spring.data.commons;
    requires pl.kacper.starzynski.employeeGratification.sharedKernel;
    requires spring.context;
}