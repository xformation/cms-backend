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
            cm.createCache(com.synectiks.cms.repositories.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.synectiks.cms.repositories.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.College.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Branch.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Department.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Batch.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Subject.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Section.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Term.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Student.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Teacher.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AcademicYear.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Holiday.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Teach.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AttendanceMaster.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Lecture.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.StudentAttendance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Location.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.LegalEntity.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AuthorizedSignatory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.BankAccounts.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Branch.class.getName() + ".teaches", jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.LegalEntityAuthSignatoryLink.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Country.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Currency.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.State.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.City.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AdminOverview.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AdminAttendance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.FeeCategory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Facility.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.TransportRoute.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.FeeDetails.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.DueDate.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.PaymentRemainder.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.LateFee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.StudentFee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Reports.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.IdCard.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Documents.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Invoice.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.CompetitiveExam.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AcademicHistory.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AdmissionApplication.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AdmissionEnquiry.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.AcademicExamSetting.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.MetaLecture.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.TypeOfGrading.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.StudentExamReport.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.StudentFacilityLink.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Library.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Vehicle.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Contract.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Insurance.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Employee.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Modules.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Book.class.getName(), jcacheConfiguration);
			cm.createCache(com.synectiks.cms.entities.ExceptionRecord.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.PaymentRequestResponse.class.getName(), jcacheConfiguration);
            cm.createCache(com.synectiks.cms.entities.Notifications.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
