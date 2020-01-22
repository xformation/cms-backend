package com.synectiks.cms.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link FeeDetailsSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class FeeDetailsSearchRepositoryMockConfiguration {

    @MockBean
    private FeeDetailsSearchRepository mockFeeDetailsSearchRepository;

}
