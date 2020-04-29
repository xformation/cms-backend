package com.synectiks.cms.repository.search;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

/**
 * Configure a Mock version of LibrarySearchRepository to test the
 * application without starting Elasticsearch.
 */
@Configuration
public class LibrarySearchRepositoryMockConfiguration {

    @MockBean
    private LibrarySearchRepository mockLibrarySearchRepository;

}
