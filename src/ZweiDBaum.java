

class ZweiDBaum implements PunktBaum {
    private Knot  wurzeln;
    private boolean akt;
    
    @Override
    public boolean insert(Punkt p) {
            Knot aktPunkt = wurzeln;
            Knot vorfahr = null;
            this.akt = false;

            if(!this.akt){
                if(aktPunkt.x > p.getX()){
                    vorfahr = aktPunkt;
                    aktPunkt = aktPunkt.links;
                }else if (aktPunkt.x < p.getX()){
                    vorfahr = aktPunkt;
                    aktPunkt = aktPunkt.recht;
             }
            }else {
                if (aktPunkt.y > p.getY()){
                    vorfahr= aktPunkt;
                    aktPunkt = aktPunkt.links;
                }else if(aktPunkt.y < p.getY()){
                    vorfahr = aktPunkt;
                    aktPunkt = aktPunkt.recht;
                }
            }this.akt = !this.akt;
            
        
        return true;
    }

    @Override
    public Punkt get(int x, int y) {
        Knot aktPunkt = wurzeln;
        boolean gefunden = false;
        this.akt = false;

        if(!this.akt){
            if(aktPunkt.x > x){
                aktPunkt = aktPunkt.links;
            }else if(aktPunkt.x < x){
                aktPunkt = aktPunkt.recht;
            }else {
                if(aktPunkt.y == y){
                    gefunden = true;
                    return aktPunkt.point;
                }
            }
        }else{
            if(aktPunkt.y > y){
                aktPunkt = aktPunkt.links;
            }else if(aktPunkt.y < y){
                aktPunkt = aktPunkt.recht;
            }else {
                if(aktPunkt.x == x){
                    gefunden = true;
                    return aktPunkt.point;
                }
            }

        }
        return null;
    }


	

	
}
