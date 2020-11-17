package com.example.agendapersonal_asiergoienetxea;

public class ObjetoTarea {
    private String nomTarea;
    private String usuario;
    private String DescTarea;
    private String FechaTarea;
    private int CosteTarea;
    private  String SpinnerPrio;
    private  String realizada;

    public ObjetoTarea(String usuario,String nomTarea, String descTarea, String fechaTarea, int costeTarea, String spinnerPrio, String realizada) {
        this.usuario= usuario;
        this.nomTarea = nomTarea;
        this.DescTarea = descTarea;
        this.FechaTarea = fechaTarea;
        this.CosteTarea = costeTarea;
        this.SpinnerPrio = spinnerPrio;
        this.realizada = realizada;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getRealizada() {
        return realizada;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNomTarea() {
        return nomTarea;
    }

    public void setNomTarea(String nomTarea) {
        this.nomTarea = nomTarea;
    }

    public String getDescTarea() {
        return DescTarea;
    }

    public void setDescTarea(String descTarea) {
        DescTarea = descTarea;
    }

    public String getFechaTarea() {
        return FechaTarea;
    }

    public void setFechaTarea(String fechaTarea) {
        FechaTarea = fechaTarea;
    }

    public int getCosteTarea() {
        return CosteTarea;
    }

    public void setCosteTarea(int costeTarea) {
        CosteTarea = costeTarea;
    }

    public String getSpinnerPrio() {
        return SpinnerPrio;
    }

    public void setSpinnerPrio(String spinnerPrio) {
        SpinnerPrio = spinnerPrio;
    }

    @Override
    public String toString() {
        return "ObjetoTarea{" +
                "nomTarea='" + nomTarea + '\'' +
                ", DescTarea='" + DescTarea + '\'' +
                ", FechaTarea='" + FechaTarea + '\'' +
                ", CosteTarea=" + CosteTarea +
                ", SpinnerPrio='" + SpinnerPrio + '\'' +
                '}';
    }
}
