package business;

import java.util.*;
import dataaccess.PublisherDA;
import pojo.PublisherPOJO;

public class PublisherBU {

    public PublisherPOJO getPublisher(int id) {
        PublisherDA da = new PublisherDA();
        return da.getPublisher(id);
    }

    public List<PublisherPOJO> getAllPublisher() {
        PublisherDA da = new PublisherDA();
        return da.getAllPublisher();
    }

    public List<PublisherPOJO> searchPublisher(String publisherName) {
        PublisherDA da = new PublisherDA();
        return da.searchPublisher(publisherName);
    }

    public int insertPublisher(PublisherPOJO newPublisher) {
        PublisherDA da = new PublisherDA();
        return da.insertPublisher(newPublisher);
    }

    public int updatePublisher(PublisherPOJO publisherI) {
        PublisherDA da = new PublisherDA();
        return da.updatePublisher(publisherI);
    }
    
     public int disablePublisher(int id){
         PublisherDA da = new PublisherDA();
         return da.disablePublisher(id);
    }
}