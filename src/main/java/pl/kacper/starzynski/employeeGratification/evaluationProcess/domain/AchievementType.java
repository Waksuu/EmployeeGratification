package pl.kacper.starzynski.employeeGratification.evaluationProcess.domain;

enum AchievementType {
    MAINTAINABLE("Maintainable"),
    REPEATABLE("Repeatable"),
    REPEATABLE_PARTIAL("Repeatable Partial");

    private String achievementType;

    AchievementType(String achievementType) {
        this.achievementType = achievementType;
    }
}
