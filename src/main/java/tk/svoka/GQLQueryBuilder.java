package tk.svoka;

import java.util.ArrayList;

public class GQLQueryBuilder {

    private ArrayList<GQLObject> objects = new ArrayList<>();

    public GQLQueryBuilder addObject(GQLObject object){
        objects.add(object);
        return this;
    }

    public String build(){
        String rtn ="{\"query\": \"{";
        for(GQLObject object:objects){
            rtn += object.build();
        }
        rtn +="}\", \"variables\":\"\"}";
        return rtn;
    }
    public ArrayList<GQLObject> getObjects(){
        return objects;
    }
}
