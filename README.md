# GQLQueryBuilder

Build graphQL query string 


## How to use

The use is quite simple, just write something like

```
 String graphQLQueryString  = 
                new GQLQueryBuilder().addObject(
                new GQLObject("publications")
                        .addParam("isArchived",true)
                        .addField("title")
                        .addField("text")
                        .addField(
                                new GQLObject("author")
                                        .setNeedNode(false)
                                        .addField("firstName")
                                        .addField("lasttName")
                        )
        ).build();
```



[![](https://jitpack.io/v/svoka/GQLQueryBuilder.svg)](https://jitpack.io/#svoka/GQLQueryBuilder)
