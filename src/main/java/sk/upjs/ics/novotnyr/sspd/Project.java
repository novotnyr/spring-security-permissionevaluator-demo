package sk.upjs.ics.novotnyr.sspd;

public class Project {
    private String codeName;

    public Project() {
        // empty constructor
    }

    public Project(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

}
