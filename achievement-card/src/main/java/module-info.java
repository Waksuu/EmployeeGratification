module pl.kacper.starzynski.employeeGratification.achievementCard {
    requires spring.context;
    requires lombok;
    requires spring.tx;
    requires spring.data.mongodb;
    requires spring.web;
    requires org.mongodb.driver.core;
    requires spring.data.commons;
    requires pl.kacper.starzynski.employeeGratification.sharedKernel;
    requires pl.kacper.starzynski.employeeGratification.achievementConfiguration;
}