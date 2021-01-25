module pl.kacper.starzynski.employeeGratification.achievementConfiguration {
    requires lombok;
    requires spring.tx;
    requires pl.kacper.starzynski.employeeGratification.sharedKernel;
    requires spring.data.commons;
    requires org.mongodb.driver.core;
    requires spring.data.mongodb;
    requires spring.context;
    requires pl.kacper.starzynski.employeeGratification.achievementCard;
    requires pl.kacper.starzynski.employeeGratification.achievementConfigurationApi;
}
