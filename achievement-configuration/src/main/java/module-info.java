module pl.kacper.starzynski.employeeGratification.achievementConfiguration {
    requires spring.context;
    requires lombok;
    requires spring.tx;
    requires pl.kacper.starzynski.employeeGratification.sharedKernel;
    requires pl.kacper.starzynski.employeeGratification.achievementCard;
    requires spring.data.commons;
    requires org.mongodb.driver.core;
    requires spring.data.mongodb;
}
