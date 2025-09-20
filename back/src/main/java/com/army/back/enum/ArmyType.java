public enum ArmyType {
    ARMY(540),
    NAVY(600),
    AIRFORCE(630),
    MARINE(540);

    private final int day;

    ArmyType(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }
}
