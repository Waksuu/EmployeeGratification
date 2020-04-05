module pl.kacper.starzynski.employeeGratification.achievementConfiguration {
    exports pl.kacper.starzynski.employeeGratification.achievementConfiguration.application to
            pl.kacper.starzynski.employeeGratification.achievementCard;
    requires lombok;
    requires spring.tx;
    requires pl.kacper.starzynski.employeeGratification.sharedKernel;
    requires spring.data.commons;
    requires org.mongodb.driver.core;
    requires spring.data.mongodb;
    requires spring.context;
}
