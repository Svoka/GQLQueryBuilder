package tk.svoka;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Artem Osipov on 08/04/17.
 * ao@enlighted.ru
 */

public class GQLObject {
    private ArrayList<GQLObject> objects = new ArrayList<>();
    private ArrayList<String> fields = new ArrayList<>();
    private String objectName;
    public GQLObject(String objectName){
        this.objectName = objectName;
    }
    private Boolean needNode = true;
    private HashMap<String,String> params = new HashMap<>();
    private HashMap<String,Integer> intParams = new HashMap<>();
    private HashMap<String,Long> longParams = new HashMap<>();
    private HashMap<String,Boolean> boolParams = new HashMap<>();


    public GQLObject addField(String field){
        fields.add(field);
        return this;
    }
    public GQLObject addField(GQLObject field){
        objects.add(field);
        return this;
    }
    public String build(){
        String rtn = objectName;

        if(getParams().size()>0 || getIntParams().size()>0){
            rtn = rtn.concat("(");
            for (String key : getParams().keySet()) {
                rtn += ""+key+":\\\""+getParams().get(key)+"\\\"";
            }
            for (String key : getIntParams().keySet()) {
                rtn += ""+key+":"+getIntParams().get(key)+"";
            }
            for (String key : getLongParams().keySet()) {
                rtn += ""+key+":"+getLongParams().get(key)+"";
            }
            for (String key : getBoolParams().keySet()) {
                rtn += ""+key+":"+(getBoolParams().get(key)?"true":"false")+"";
            }

            rtn = rtn.concat(")");
        }
        rtn = rtn.concat((needNode?"{edges{node{":"{"));



        for(String field:fields){
            rtn += field+",";
        }
        for(GQLObject object:objects){
            rtn += object.build();
        }
        rtn += needNode?"}}}":"}";
        return rtn;
    }
    public String getObjectName(){
        return objectName;
    }

    public GQLObject setNeedNode(Boolean isNeed){
        this.needNode=isNeed;
        return this;
    }

    private HashMap<String,String> getParams() {
        return params;
    }
    private HashMap<String,Integer> getIntParams() {
        return intParams;
    }
    private HashMap<String,Long> getLongParams() {
        return longParams;
    }
    private HashMap<String,Boolean> getBoolParams() {
        return boolParams;
    }

    public GQLObject addParam(String key, String value) {
        this.params.put(key,value);
        return this;
    }
    public GQLObject addParam(String key, Integer value) {
        this.intParams.put(key,value);
        return this;
    }
    public GQLObject addParam(String key, Long value) {
        this.longParams.put(key,value);
        return this;
    }
    public GQLObject addParam(String key, Boolean value) {
        this.boolParams.put(key,value);
        return this;
    }


}
