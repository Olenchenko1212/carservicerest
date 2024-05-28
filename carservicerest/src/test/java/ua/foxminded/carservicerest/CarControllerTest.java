package ua.foxminded.carservicerest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest {

	@Container
	@ServiceConnection
	static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16.0");

	@Autowired
	MockMvc mockMvc;

	@BeforeAll
	static void beforeAll() {
		postgres.start();
	}

	@AfterAll
	static void afterAll() {
		postgres.stop();
	}

	@DynamicPropertySource
	static void setDataSourceProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.username", postgres::getUsername);
		registry.add("spring.datasource.password", postgres::getPassword);
		registry.add("spring.datasource.url", postgres::getJdbcUrl);
		registry.add("spring.jpa.hibernate.ddl-auto", () -> "create");
	}

	@Test
	void whenRequestForCreateIsValidExpectNewCar() throws Exception {

		var requestBuilder = MockMvcRequestBuilders.post("/api/v1/cars").contentType(MediaType.APPLICATION_JSON)
				.content(
						"""
							{"carCode": "i6jT5ByjBF", "make": "Ford", "model": "Ranger SuperCab", "year": 2020, "categories": []}
						""");

		mockMvc.perform(requestBuilder).andExpect(jsonPath("$.carCode").value("i6jT5ByjBF"))
				.andExpect(jsonPath("$.make").value("Ford")).andExpect(jsonPath("$.model").value("Ranger SuperCab"))
				.andExpect(jsonPath("$.year").value("2020")).andDo(print()).andExpectAll(status().isCreated(),
						header().string(HttpHeaders.LOCATION,
								"http://localhost/api/v1/cars/makers/Ford/models/Ranger%20SuperCab/year/2020"),
						content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	}

	@Test
	void whenRequestForCreateIsNotValidExpectExeption() throws Exception {

		var requestBuilder = MockMvcRequestBuilders.post("/api/v1/cars").contentType(MediaType.APPLICATION_JSON)
				.content("""
							{"carCode": "", "make" : "", "model" : "", "year" : null, "categories": []}
						""");

		mockMvc.perform(requestBuilder).andDo(print()).andExpectAll(status().isBadRequest(),
				content().contentTypeCompatibleWith(MediaType.APPLICATION_PROBLEM_JSON), content().json("""
						{"errors" : [
								"carCode : the size must be within the range of 1 to 15 symbols",
								"make : the size must be within the range of 1 to 30 symbols",
								"model : the size must be within the range of 1 to 30 symbols",
								"year : positive number, min 1900 is required"]
						}"""));
	}

	@Test
	@Sql("/sql/createData.sql")
	void whenRequestListCarsExpectPageWithCars() throws Exception {

		var requestBuilder = MockMvcRequestBuilders.get("/api/v1/cars").param("make", "Ford");

		this.mockMvc.perform(requestBuilder).andDo(print()).andExpectAll(status().isOk(),
				content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
				jsonPath("$.content.length()").value(1),
				jsonPath("$.content[0].id").value("1"),
				jsonPath("$.content[0].carCode").value("j7cAeeW8ny"),
				jsonPath("$.content[0].make").value("Ford"),
				jsonPath("$.content[0].model").value("F150 SuperCrew Cab"),
				jsonPath("$.content[0].year").value("2016"));
	}

	@Test
	@Sql("/sql/createData.sql")
	void whenRequestToGetCarButNotExistExpectEmptyResponse() throws Exception {

		var requestBuilder = MockMvcRequestBuilders.get("/api/v1/cars").param("make", "Honda");

		mockMvc.perform(requestBuilder).andDo(print()).andExpectAll(status().isOk(),
				content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON),
				jsonPath("$.content.length()").value(0));
	}

	@Test
	@Sql("/sql/createData.sql")
	void whenUpdateCarRequestIsValidExpectNoContent() throws Exception {

		var requestBuilder = MockMvcRequestBuilders.patch("/api/v1/cars/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(
						"""
							{"carCode": "i6jT5ByjBF", "make": "Ford1", "model": "Ranger SuperCab 1", "year": 2020, "categories": []}
						""");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpectAll(status().isNoContent());
	}

	@Test
	@Sql("/sql/createData.sql")
	void whenDeleteCarRequestIsValidExpectNoContent() throws Exception {

		var requestBuilder = MockMvcRequestBuilders.delete("/api/v1/cars/1");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpectAll(status().isNoContent());
	}
	
	@Test
	@Sql("/sql/createData.sql")
	void whenDeleteCarRequestButNotFoundExpectNoSuchElementException() throws Exception {

		var requestBuilder = MockMvcRequestBuilders.delete("/api/v1/cars/999999");

		mockMvc.perform(requestBuilder)
			.andDo(print())
			.andExpectAll(status().isNotFound());
	}
}
