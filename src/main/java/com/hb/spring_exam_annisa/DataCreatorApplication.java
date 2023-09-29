package com.hb.spring_exam_annisa;

import com.hb.spring_exam_annisa.models.News;
import com.hb.spring_exam_annisa.models.Category;
import com.hb.spring_exam_annisa.models.Role;
import com.hb.spring_exam_annisa.models.User;
import com.hb.spring_exam_annisa.repositories.NewsRepository;
import com.hb.spring_exam_annisa.repositories.CategoryRepository;
import com.hb.spring_exam_annisa.services.RoleService;
import com.hb.spring_exam_annisa.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.GregorianCalendar;

@SpringBootApplication
public class DataCreatorApplication {
    private Logger logger = LoggerFactory.getLogger(DataCreatorApplication.class);


    public static void main(String[] args){
        SpringApplication.run(DataCreatorApplication.class);
    }

    @Bean
    public CommandLineRunner dataLoader(
            UserService userService,
            RoleService roleService,
            CategoryRepository categoryRepository,
            NewsRepository newsRepository
    ) {
        return args -> {
            logger.info("Role and User data creation");
            Role roleJournalist = roleService.findByRoleName("Journalist");
            if(roleJournalist == null){
                Role role = new Role();
                role.setRoleName("Journalist");
                roleJournalist = role;
                roleService.createRole(roleJournalist);
            }

            Role roleReader = roleService.findByRoleName("Reader");
            if(roleReader == null){
                Role role = new Role();
                role.setRoleName("Reader");
                roleReader = role;
                roleService.createRole(roleReader);
            }

            if(userService.findByEmail("journalist@mail.test").isEmpty()){
                User journalist = new User();
                journalist.setEmail("journalist@mail.test");
                journalist.setFirstname("journalist-firstname");
                journalist.setLastname("journalist-lastname");
                journalist.setPassword("pass123");
                journalist.addRole(roleJournalist);
                userService.saveUser(journalist);
            }

            if(userService.findByEmail("reader1@mail.test").isEmpty()){
                User reader1 = new User();
                reader1.setEmail("reader1@mail.test");
                reader1.setFirstname("reader1-firstname");
                reader1.setLastname("reader1-lastname");
                reader1.setPassword("pass456");
                reader1.addRole(roleReader);
                userService.saveUser(reader1);
            }


            logger.info("Category and News data creation");
            Category category1 = categoryRepository.findByCategoryName("Sports");
            if(category1 == null){
                category1 = new Category("Sports");
                categoryRepository.save(category1);
            }

            Category category2 = categoryRepository.findByCategoryName("Politics");
            if(category2 == null){
                category2 = new Category("Politics");
                categoryRepository.save(category2);
            }

            Category category3 = categoryRepository.findByCategoryName("Events");
            if(category3 == null){
                category3 = new Category("Events");
                categoryRepository.save(category3);
            }

            Category category4 = categoryRepository.findByCategoryName("News in Brief");
            if(category4 == null){
                category4 = new Category("News in Brief");
                categoryRepository.save(category4);
            }

            if(newsRepository.findByTitle("News 1") == null){
                News news1 = new News();
                news1.setTitle("News 1");
                news1.setImage("/images/news1.jpg");
                news1.setContent("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.");
                news1.setPublicationDate(new GregorianCalendar(2022,6,10));
                news1.setCategory(category1);
                newsRepository.save(news1);
            }

            if(newsRepository.findByTitle("News 2") == null){
                News news2 = new News();
                news2.setTitle("News 2");
                news2.setImage("/images/news2.jpg");
                news2.setContent("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident.");
                news2.setPublicationDate(new GregorianCalendar(2023,7,25));
                news2.setCategory(category1);
                newsRepository.save(news2);
            }

            if(newsRepository.findByTitle("News 3") == null){
                News news3 = new News();
                news3.setTitle("News 3");
                news3.setImage("/images/news3.jpg");
                news3.setContent("At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupidita provident. Et harum quidem rerum facilis est et expedita distinctio.");
                news3.setPublicationDate(new GregorianCalendar(2023,5,20));
                news3.setCategory(category2);
                newsRepository.save(news3);
            }

            if(newsRepository.findByTitle("News 4") == null){
                News news4 = new News();
                news4.setTitle("News 4");
                news4.setImage("/images/news4.jpg");
                news4.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit. At consequatur dolorem molestias. Adipisci earum ex ut vero. A autem itaque, molestias placeat sequi sint! Aliquam nam neque nobis placeat repudiandae.");
                news4.setPublicationDate(new GregorianCalendar(2023,5,17));
                news4.setCategory(category4);
                newsRepository.save(news4);
            }

            if(newsRepository.findByTitle("News 5") == null){
                News news5 = new News();
                news5.setTitle("News 5");
                news5.setImage("/images/news5.jpg");
                news5.setContent("Lorem ipsum dolor sit amet, consectetur adipisicing elit. At consequatur dolorem molestias. Adipisci earum ex ut vero. A autem itaque, molestias placeat sequi sint! Aliquam nam neque nobis placeat repudiandae.");
                news5.setPublicationDate(new GregorianCalendar(2023,6,10));
                news5.setCategory(category4);
                newsRepository.save(news5);
            }
        };
    }

}
