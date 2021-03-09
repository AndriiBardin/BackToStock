package com.company.backinstock.service;

import com.company.backinstock.model.Notification;
import com.company.backinstock.model.UserPriority;
import java.util.List;
import java.util.stream.Collectors;

public class NotifyService {

    List<Notification> notificationsHigh;
    List<Notification> notificationsNormal;
    List<Notification> notificationsLow;


    void send(List<Notification> notifications) {
        prioritize(notifications);
        notificationsHigh.forEach(this::notify);
        notificationsNormal.forEach(this::notify);
        notificationsLow.forEach(this::notify);
    }

    void prioritize(List<Notification> notifications) {
        notificationsHigh = notifications.stream()
                .filter(job -> job.getPriority().equals(UserPriority.HIGH.get()))
                .collect(Collectors.toList());
        notificationsNormal = notifications.stream()
                .filter(job -> job.getPriority().equals(UserPriority.MEDIUM.get()))
                .collect(Collectors.toList());
        notificationsLow = notifications.stream()
                .filter(job -> job.getPriority().equals(UserPriority.LOW.get()))
                .collect(Collectors.toList());
    }

    private void notify(Notification job) {
        System.out.printf("USER:%s, ID:%s, Age:%s, Premium:%s, Product:%s, Category:%s, Priority:%s%n ",
                job.getUser().getName(),
                job.getUser().getId(),
                job.getUser().getAge(),
                job.getUser().isPremium(),
                job.getProduct().getName(),
                job.getProduct().getCategory(),
                job.getPriority());
    }
}