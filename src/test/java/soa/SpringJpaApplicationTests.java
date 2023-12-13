package soa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import soa.controller.ProduitRESTController;
import soa.metier.ProduitMetierImpl;
import soa.repository.ProduitRepository;

@SpringBootTest
class SpringJpaApplicationTests {
	private MockMvc mockMvc;
	@Mock
	private ProduitMetierImpl produitMetier;
	@Mock
	private ProduitRepository produitRepository;
	@InjectMocks
	private ProduitRESTController produitRESTController;
//	@BeforeEach
//	public void setup(){
//		MockitoAnnotations.openMocks(this);
//		mockMvc = MockMvcBuilder.standaloneSetup(statistiquesCon)
//	}

	private
	@Test
	void contextLoads() {
	}

}
