package owlsdevelopers.org.passcoder.view

import org.junit.*
import org.koin.standalone.StandAloneContext.closeKoin
import org.koin.standalone.StandAloneContext.startKoin
import owlsdevelopers.org.passcoder.domain.models.repository.PasscodeRepository
import owlsdevelopers.org.passcoder.ui.addpasscode.viewmodels.AddPasscodeViewModel

class DetailViewModelTest : KoinTest {

    val viewModel: AddPasscodeViewModel by inject { mapOf("id" to "ID") }
    val repository: PasscodeRepository by inject()

   // @Mock
   // lateinit var uiData: Observer<DailyForecastModel>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun before() {
        //MockitoAnnotations.initMocks(this)
        startKoin(testApp)
    }

    @After
    fun after() {
        closeKoin()
    }

    @Test
    fun testGotDetail() {
        // Setup data
        repository.searchWeather("Toulouse").blockingGet()
        val list = repository.getWeather().blockingGet()

        // Observe
        //viewModel.uiData.observeForever(uiData)

        // Select data to notify
        val weather = list.first()
        viewModel.getDetail(weather.id)

        // Has received data
        Assert.assertNotNull(viewModel.uiData.value)

        // Has been notified
        //Mockito.verify(uiData).onChanged(weather)
    }
}