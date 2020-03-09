package com.synectiks.cms.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of {@link ContractSearchRepository} to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class ContractSearchRepositoryMockConfiguration {

    @MockBean
    private ContractSearchRepository mockContractSearchRepository;

}
