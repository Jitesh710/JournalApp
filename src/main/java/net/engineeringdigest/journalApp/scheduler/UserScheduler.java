package net.engineeringdigest.journalApp.scheduler;


import net.engineeringdigest.journalApp.cache.CacheService;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepositoryImpl;
import net.engineeringdigest.journalApp.service.EmailService;
import net.engineeringdigest.journalApp.service.SentimentAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserScheduler {

    @Autowired
    private UserRepositoryImpl userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SentimentAnalysisService sentimentAnalysisService;

    @Autowired
    private CacheService cacheService;

    @Scheduled(cron = "0 0 9 ? * SUN")
    //@Scheduled(cron = "0 * * ? * *")
    public void fetchUserAndSendSAmail() {
        List<User> users = userRepository.getUsersForSentimentAnalysis();
        for(User user: users) {
            List<JournalEntry> journalEntries = user.getJournalEntries();
            List<String> filteredEntries = journalEntries.stream().filter(x -> x.getDate().isAfter(LocalDateTime.now().minus(7, ChronoUnit.DAYS))).map(x -> x.getContent()).collect(Collectors.toList());
            String entry = String.join(" ", filteredEntries);
            String sentiment = sentimentAnalysisService.getSentiment(entry);

            emailService.sendMail(user.getEmail(), "sentiment for 7 days", sentiment);
        }
    }

    //this will ensure that cache is cleared and refetched every 10 minutes
    @Scheduled(cron = "0 0/10 * ? * *")
    public void clearAppCache() {
        cacheService.init();
    }
}
