package sample.daytoursnyttsdk;

public class Participant {
    private String name;
    private String phoneNr;
    private String email;
    private String kennitala;
    private int fjoldi;

    public Participant(String nafn, String simanumer, String tolvupostur, String kt, int fjoldi){
        name = nafn;
        phoneNr = simanumer;
        email = tolvupostur;
        kennitala = kt;
        this.fjoldi = fjoldi;
    }
}
