# spring-crud-generics
spring crud generic 

#Alert
In development. Help us! Make a fork!

#Install

    <repository>
      <id>jitpack.io</id>
      <url>https://jitpack.io</url>
    </repository>

    <dependency>
      <groupId>com.github.NetoDevel</groupId>
      <artifactId>spring-crud-generics</artifactId>
      <version>0.1.1</version>
    </dependency>

#Documentation

###Endpoints

     BASE_URL /index GET
     BASE_URL /new GET
     BASE_URL /new POST
     BASE_URL {id}/update GET
     BASE_URL {id}/update PUT
     BASE_URL {id}/delete DELETE
  
###Structure HTML Files (Thymeleaf)

    templates/contact/index/index.html
    templates/contact/new/new.html

#Usage
Example controller crud generic

    @Controller
    @RequestMapping("/contacts")
    public class ContactController extends AbstractControllerCrud<Contact, Integer> {

      @Autowired
      private ContactRepository contactRepository;

      @Override
      public JpaRepository<Contact, Integer> getRepository() {
        return contactRepository;
      }

      @Override
      public String getPathHtmlFiles() {
        return "contact";
      }

      @Override
      public String[] getMsgActions() {
        String[] msgs = {"Contact save.", "Error"};
        return msgs;
      }

      @Override
      public Class<Contact> getClazz() {
        return Contact.class;
      }

      @Override
      public String getBaseURL() {
        return "contacts";
      }

    }



