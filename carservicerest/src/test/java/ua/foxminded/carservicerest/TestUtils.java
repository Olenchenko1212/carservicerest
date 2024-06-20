package ua.foxminded.carservicerest;

public final class TestUtils {
	
	private TestUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	protected static final String CARDTO_JSON = "{\"carCode\": \"i6jT5ByjBF\", \"make\": \"Ford\", \"model\": \"Ranger SuperCab\", \"year\": 2020, \"categories\": []}";
	protected static final String EMPTY_CARDTO_JSON = "{\"carCode\": \"\", \"make\" : \"\", \"model\" : \"\", \"year\" : null, \"categories\": []}";
	protected static final String ERRORS_JSON = "{\"errors\" : [\r\n"
							+ "				\"carCode : the size must be within the range of 1 to 15 symbols\",\r\n"
							+ "				\"make : the size must be within the range of 1 to 30 symbols\",\r\n"
							+ "				\"model : the size must be within the range of 1 to 30 symbols\",\r\n"
							+ "				\"year : positive number, min 1900 is required\"]\r\n"
							+ "				}";
	protected static final String UPDATE_CARDTO_JSON = "{\"carCode\": \"i6jT5ByjBF\", \"make\": \"Ford1\", \"model\": \"Ranger SuperCab 1\", \"year\": 2020, \"categories\": []}";
}
