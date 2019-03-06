package com.synectiks.cms.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.synectiks.cms.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.synectiks.cms.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.College.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Branch.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Batch.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Subject.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Section.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Term.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Student.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Teacher.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.AcademicYear.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Holiday.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Teach.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.AttendanceMaster.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Lecture.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.StudentAttendance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Location.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.LegalEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.AuthorizedSignatory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.BankAccounts.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Branch.class.getName() + ".teaches", jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.LegalEntityAuthSignatoryLink.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Country.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Currency.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.State.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.City.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.AdminOverview.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.AdminAttendance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.FeeCategory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Facility.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.TransportRoute.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.FeeDetails.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.DueDate.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.PaymentRemainder.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.LateFee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.StudentFee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Reports.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.IdCard.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Documents.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.Invoice.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.domain.AcademicHistory.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
