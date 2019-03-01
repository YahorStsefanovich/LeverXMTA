package com.leverx.leverxspringproj.dao;

import com.leverx.leverxspringproj.intfce.IOdataDao;
import com.leverx.leverxspringproj.model.Category;
import com.leverx.leverxspringproj.model.Product;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryBuilder;
import com.sap.cloud.sdk.odatav2.connectivity.ODataQueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class ODataDao implements IOdataDao {

    private static final Logger logger = LoggerFactory.getLogger(ODataDao.class);
    private final String SERVICE_PATH = "/V2/OData/OData.svc/";
    private final String SERVICE_ENTITY = "Products";

    public List<Product> getAll(String destinationName) {
        List<Product> productList = new ArrayList<>();

        try {
            ODataQueryResult result = ODataQueryBuilder.withEntity(SERVICE_PATH , SERVICE_ENTITY)
                    .select("ID", "Name", "Description", "ReleaseDate", "Price", "Category").expand("Category")
                    .build()
                    .execute(destinationName);
            List<Map<String, Object>> listMap = result.asListOfMaps();
            productList = getListFromResultSet(listMap);
            logger.info("Products list: " + getListFromResultSet(listMap));

        } catch (ODataException e) {
            logger.error("Can't get list of entities: " + e.getMessage());
        }

        return productList;
    }

    private List<Product> getListFromResultSet(List<Map<String, Object>> listMap){
        List<Product> productList = new ArrayList<>();

        listMap.forEach(item -> {
            Product product = new Product(
                    (int)(item.get("ID")),
                    (String)item.get("Name"),
                    (String)item.get("Description"),
                    (GregorianCalendar)item.get("ReleaseDate"),
                    (BigDecimal)item.get("Price")
            );
            product.setCategory(getCategory((Map<String, Object>)item.get("Category")));
            productList.add(product);
        });

        return productList;
    }

    private Category getCategory(Map<String, Object> item){

        return new Category(
                (int)item.get("ID"),
                (String) item.get("Name")
        );
    }

    @Override
    public List<Product> getAll(){
        throw new NotImplementedException();
    }

    @Override
    public Optional<Product> getById(String id){
        throw new NotImplementedException();
    }

    @Override
    public Product createEntity(Product entity){
        throw new NotImplementedException();
    }

    @Override
    public String deleteEntity(String key){
        throw new NotImplementedException();
    }

    @Override
    public Product updateEntity(Product entity, String id) {
        throw new NotImplementedException();
    }
}
