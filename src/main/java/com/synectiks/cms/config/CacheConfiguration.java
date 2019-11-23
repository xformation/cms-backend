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
            cm.createCache(com.synectiks.commons.entities.cms.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.College.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Branch.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Batch.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Subject.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Section.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Term.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Student.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Teacher.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AcademicYear.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Holiday.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Teach.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AttendanceMaster.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Lecture.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.StudentAttendance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Location.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.LegalEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AuthorizedSignatory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.BankAccounts.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Branch.class.getName() + ".teaches", jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.LegalEntityAuthSignatoryLink.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Country.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Currency.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.State.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.City.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AdminOverview.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AdminAttendance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.FeeCategory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Facility.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.TransportRoute.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.FeeDetails.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.DueDate.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.PaymentRemainder.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.LateFee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.StudentFee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Reports.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.IdCard.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Documents.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Invoice.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.CompetitiveExam.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AcademicHistory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AdmissionApplication.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AdmissionEnquiry.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.AcademicExamSetting.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.MetaLecture.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.TypeOfGrading.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.StudentExamReport.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.StudentFacilityLink.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Library.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Vehicle.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Contract.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Insurance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Employee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Modules.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Book.class.getName(), jcacheConfiguration);
			cm.createCache(com.synectiks.commons.entities.cms.ExceptionRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.PaymentRequestResponse.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.commons.entities.cms.Notifications.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
