package top.bujiaban.test.contracts;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.context.jdbc.Sql;

@Sql("/sql/prepare_data_for_fetch_latest_upload_info.sql")
@Sql(value = "/sql/truncate_table_data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class DevopsInboundsBase extends TestBase {

    @BeforeEach
    public void setUp() {
        super.setUp();
        mockCurrentUser();
    }
}
