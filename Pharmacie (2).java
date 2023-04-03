class Medicament{
    private String nom;
    private int nb;
    private double pua,marge;
    private boolean ramb;
    public Medicament(String nom,int nb,double marge,double pua,boolean ramb){
        this.nom = new String(nom);
        this.nb=nb;
        this.marge =marge;
        this.pua = pua;
        this.ramb = ramb;
    }
    void setNom(String nom){
        this.nom = new String(nom);
    }
    void setnb(int nb){
        this.nb=nb;
    }
    void setpua(double pua){
        this.pua =pua;
    }
    void setmarge(double marge){
        this.marge = marge;
    }
    void setramb(boolean ramb){
        this.ramb = ramb;
    }
    String getNom(){
        return nom;
    }
    int getnb(){
        return nb;
    }
    double getpua(){
        return pua;
    }
    double getmarge(){
        return marge;
    }
    boolean getramb(){
        return ramb;
    }
}
class Stock{
    Node [] Tableau=new Node[26];
    static class Node{
        char car;
        MedicamentL ListMed;
        Node(char d)
        {
            this.car = d;
            ListMed = null;
        }
    }
    static class MedicamentL{
        Medicament med;
        MedicamentL next;
        MedicamentL(Medicament med){
            this.med = med;
            next = null;
        }
    }
    public Stock(){
        for(int i=0; i<26; i++){
            Tableau[i]=new Node((char) (i+97));
        }
    } 
    boolean recherche(String Med){
        for(int i=0; i<26; i++){
            if(Tableau[i].car==Med.charAt(0)){
                MedicamentL m=Tableau[i].ListMed;
                while(m!=null){
                    if(m.med.getNom().compareToIgnoreCase(Med)==0)
                       return true;
                    m=m.next;
                }    
            }
        }
        return false;
    }
    void achat(Medicament m){
        for(int i=0; i<26; i++){
            if(Tableau[i].car==m.getNom().charAt(0)){
                if(Tableau[i].ListMed==null){
                    MedicamentL L=new MedicamentL(m);
                    Tableau[i].ListMed=L;
                }else{
                MedicamentL q=Tableau[i].ListMed,l=Tableau[i].ListMed;
                while(l!=null &&l.med.getNom().compareToIgnoreCase(m.getNom())<0){      
                    q=l;
                    l=l.next;
                }
                if(l!=null&&l.med.getNom().compareToIgnoreCase(m.getNom())==0){
                    l.med.setpua(m.getpua());
                    l.med.setnb(l.med.getnb()+m.getnb());
                    return;
                }
                else {
                    MedicamentL L=new MedicamentL(m);
                    q.next=L;
                    L.next=l;
                }
              }
            }
        }
    }
    double Vente(Client cl,Medicament med,int nb){
        for(int i=0; i<26; i++){
            if(Tableau[i].car==med.getNom().charAt(0)){
                MedicamentL q=Tableau[i].ListMed,l=Tableau[i].ListMed;
                while(l!=null){
                    if(l.med.getNom().compareToIgnoreCase(med.getNom())==0 && med.getnb()>=nb){ 
                        double vent;
                        if(cl.assure && med.getramb()){
                            vent=nb*med.getpua()*0.2 +med.getmarge()*med.getpua()*nb;
                        }
                        else {
                            vent=nb*med.getpua()+med.getmarge()*med.getpua()*nb;
                        }
                        l.med.setnb(l.med.getnb()-nb);
                        if(l.med.getnb()==0 && l==Tableau[i].ListMed){
                            Tableau[i].ListMed=l.next;
                            l=null;
                        }else if(l.med.getnb()==0){
                            q.next=l.next;
                            l=null;
                        }
                        return vent;
                    }
                    q=l;
                    l=l.next;
                }
                  
            }
        }
        return 0;
    }    
    double valStock(){
        double sum=0;
        for(int i=0; i<26; i++){
                MedicamentL l=Tableau[i].ListMed;
                while(l!=null){
                    sum+=l.med.getnb()*l.med.getpua();
                    l=l.next;
                }
            }
            return sum;
        }
}
class Client{
    String nom,prenom;
    int date_naissance;
    boolean assure;
    int nss;
    static int i=1;
    public Client(String nom,String prenom,int j,boolean assure){
        this.nom=new String(nom);
        this.prenom=new String(prenom);
        this.date_naissance=j;
        this.assure=assure;
        if(this.assure){
            nss=i;
            i++;
        }   
    }
}
public class Pharmacie{
    public static void main(String[] args){
        Stock st=new Stock();
        Medicament m1=new Medicament("abufene",232,0.2,10,true);
        Medicament m2=new Medicament("bctifd",199,0.6,96,false);
        Medicament m3=new Medicament("ab",232,0.9,70,true);
        Medicament m4=new Medicament("bactrim",50,0.8,69,true);
        Medicament m5=new Medicament("baum",25,0.2,10,false);
        Medicament m6=new Medicament("ca",40,0.3,58,true);
        Medicament m7=new Medicament("cb",232,0.9,30,true);
        Medicament m8=new Medicament("da",89,0.5,10,false);
        Medicament m9=new Medicament("ea",2352,0.7,60,true);
        Medicament m10=new Medicament("eb",232,0.5,10,true);
        Medicament m11=new Medicament("ec",18,0.6,20,false);
        Medicament m12=new Medicament("fa",232,0.4,58,true);
        Medicament m13=new Medicament("ga",20,0.5,10,true);
        Medicament m14=new Medicament("ha",232,0.4,90,false);
        Medicament m15=new Medicament("hd",40,0.13,15,true);
        Medicament m17=new Medicament("hb",50,0.15,45,true);
        st.achat(m1);
        st.achat(m2);
        st.achat(m3);
        st.achat(m4);
        st.achat(m5);
        st.achat(m6);
        st.achat(m7);
        st.achat(m8);
        st.achat(m9);
        st.achat(m10);
        st.achat(m11);
        st.achat(m12);
        st.achat(m13);
        st.achat(m14);
        st.achat(m15);
        st.achat(m17);
       /* System.out.println(st.Tableau[7].ListMed.med.getNom());
        System.out.println(st.recherche("hk"));
        Stock.MedicamentL l=st.Tableau[7].ListMed;
        while (l!=null){
            System.out.println(l.med.getNom());
            l=l.next;
        }*/
        /*Achat d’un médicament existant dans le stock*/
         System.out.println("********AHMED IGHILAHRIZ /GRP 04 ******************");
        System.out.println("Avant L'achat : "+ m5.getpua());
        System.out.println("Avant L'achat :"+ m5.getnb());
        Medicament m16=new Medicament("baum",100,0.4,50,false);
        st.achat(m16);
        System.out.println("Apres L'achat "+ m5.getpua());
        System.out.println("Apres L'achat "+ m5.getnb());
        
       /*Vente d’un médicament remboursable à un client assuré*/ 


       Client cl1=new Client("ahmed","ighilahriz",1966,true);
        System.out.println("Le nombre de medicament avant l achat Avant la Vente " + m1.getnb());
        System.out.println(st.Vente(cl1,m1,10));
        System.out.println("Le nombre de medicament apres l achat Avant la Vente " + m1.getnb());

        /*Vente d’un médicament remboursable à un client non assuré*/

        Client cl2=new Client("bousaad","kadour",1965,false);
        System.out.println("Le nombre de medicament Avant le Vente " + m1.getnb());
        System.out.println(st.Vente(cl2,m1,10));
        System.out.println("Le nombre de medicament Apres le Vente " + m1.getnb());

        /*Vente d’un médicament non remboursable à un client assuré*/

        Client cl3=new Client("felip ","mouras",2000,true);
        System.out.println("Le nombre de medicament  Avant le Vente " + m2.getnb());
        System.out.println(st.Vente(cl3,m2,30));
        System.out.println("Le nombre de medicament Apres le Vente " + m2.getnb());
        
        /*Vente d’un médicament avec un stock limite (0 boites après la vente)*/
        
        System.out.println("Le nombre de medicament Avant le Vente " + m6.getnb());
        System.out.println(st.Vente(cl3,m6,40));
        System.out.println("Le nombre de medicament apres le Vente " + m6.getnb());
        System.out.println(st.Vente(cl3,m6,30));
        System.out.println(st.Tableau[2].ListMed.med.getNom());
        
    }

}

