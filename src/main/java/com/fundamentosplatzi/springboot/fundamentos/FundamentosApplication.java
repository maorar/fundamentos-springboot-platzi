package com.fundamentosplatzi.springboot.fundamentos;

import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBean22;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithDependency;
import com.fundamentosplatzi.springboot.fundamentos.bean.MyBeanWithProperties;
import com.fundamentosplatzi.springboot.fundamentos.component.ComponentDependency;
import com.fundamentosplatzi.springboot.fundamentos.entity.User;
import com.fundamentosplatzi.springboot.fundamentos.pojo.UserPojo;
import com.fundamentosplatzi.springboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class FundamentosApplication implements CommandLineRunner {

	private final Log LOGGER = LogFactory.getLog(FundamentosApplication.class);


	private ComponentDependency componentDependency;
	private MyBean myBean;
	private MyBean22 myBean22;
	private MyBeanWithDependency myBeanWithDependency;
	private MyBeanWithProperties myBeanWithProperties;
	private UserRepository userRepository;
	private UserPojo userPojo;

		public FundamentosApplication(@Qualifier("componentImplement")
										  ComponentDependency componentDependency,
								  MyBean myBean,
								  MyBean22 myBean22,
								  MyBeanWithDependency myBeanWithDependency,
								  MyBeanWithProperties myBeanWithProperties,
                                  UserRepository userRepository,
								  UserPojo userPojo
	) {
		this.componentDependency = componentDependency;
		this.myBean = myBean;
		this.myBean22 = myBean22;
		this.myBeanWithDependency = myBeanWithDependency;
		this.myBeanWithProperties = myBeanWithProperties;
		this.userRepository = userRepository;
		this.userPojo = userPojo;

	}



	public static void main(String[] args) {
		SpringApplication.run(FundamentosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//ejemplosAnteriores();
		saveUsersInDataBase();
		getInformationJpqlFromUser();
	}
	private void getInformationJpqlFromUser(){
			/*LOGGER.info("usuario con el metodo findUserEmail " +
					userRepository.findMyUserEmail("john@domain.com").orElseThrow(()-> new RuntimeException("No se encontro" +
				" el usuario ")));

			userRepository.findAndSort("Ju", Sort.by("id").descending())
						.stream()
						.forEach(user -> LOGGER.info("Usuario con metodo sort " + user));

			userRepository.findByName("Julie6").stream().forEach(user ->LOGGER.info("Usuario con query method" + user));

			LOGGER.info("Usuario con Query Method findByEmailAnaName " + userRepository.findByEmailAndName("john7@domain.com","John7")
					.orElseThrow(()-> new RuntimeException("No se encontro el usuario ")));

			userRepository.findByNameLike("%Jo%").stream().forEach(user->LOGGER.info("Usuario findNameLike"+user));

			userRepository.findByNameOrEmail(null,"john@domain.com").stream().forEach(user->LOGGER.info("Usuario findNameOrEmail"+user));

		    userRepository.findByNameOrEmail("John",null).stream().forEach(user->LOGGER.info("Usuario findNameOrEmail"+user));
*/
		    userRepository
					.findByBirthDateBetween(LocalDate.of(2021,3,1), LocalDate.of(2021,4,2))
					.stream()
					.forEach(user->LOGGER.info("Usuario en intervalo d fechas " + user));
	}

	private void saveUsersInDataBase(){
		User user1 = new User("John","john@domain.com", LocalDate.of(2021,03,20));
		User user2 = new User("Julie","julie@domain.com",LocalDate.of(2021,05,21));
		User user3 = new User("John3","john3@domain.com",LocalDate.of(2021,03,23));
		User user4 = new User("Julie4","julie4@domain.com",LocalDate.of(2021,05,24));
		User user5 = new User("John5","john5@domain.com",LocalDate.of(2021,03,25));
		User user6 = new User("Julie6","julie6@domain.com",LocalDate.of(2021,05,26));
		User user7 = new User("John7","john7@domain.com",LocalDate.of(2021,03,27));
		User user8 = new User("Julie6","julie8@domain.com",LocalDate.of(2021,05,28));
		User user9 = new User("John9","john9@domain.com",LocalDate.of(2021,03,29));
		User user10 = new User("Julie10","julie10@domain.com",LocalDate.of(2021,05,30));
		User user11 = new User("John11","john11@domain.com",LocalDate.of(2021,11,29));
		User user12 = new User("Julie12","julie12@domain.com",LocalDate.of(2021,12,30));

		List<User> list = Arrays.asList(user1,user2,user3,user4,user5,user6,user7,user8,user9,user10,user11,user12);
		list.stream().forEach(userRepository::save);
	}

	private void ejemplosAnteriores(){
		componentDependency.saludar();
		myBean.print();
		myBean22.imprimir();
		myBeanWithDependency.printWithDependency();
		System.out.println(myBeanWithProperties.function());
		System.out.println(userPojo.getEmail()+"-"+userPojo.getAge());

		LOGGER.error("Esto es un error del aplicativo");
	}

}
