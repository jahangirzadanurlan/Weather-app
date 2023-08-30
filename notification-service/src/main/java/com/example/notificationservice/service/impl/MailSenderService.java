package com.example.notificationservice.service.impl;

import com.example.notificationservice.model.dto.request.SubscribeRequestDto;
import com.example.notificationservice.model.dto.request.UserRegisterEmailRequestDto;
import com.example.notificationservice.model.dto.response.WeatherResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailSenderService {
    private final JavaMailSender javaMailSender;

    public void sendMail(UserRegisterEmailRequestDto request) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(request.getEmail());
            helper.setSubject("Confirm account!");

            String confirmationLink = "http://localhost:8081/auth/confirm/" + request.getToken();
            String emailContent = "<html><body><p>Please click the following link to confirm your account:</p>"
                    + "<a href=\"" + confirmationLink + "\">Confirm Account</a>"
                    + "</body></html>";

            helper.setText(emailContent, true);

            javaMailSender.send(message);
            log.info("Email sent: " + request.getEmail());
        } catch (Exception e) {
            // Handle exception
        }
    }

    public void sendWeatherMail(String email, WeatherResponseDto weather) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(email);
            helper.setSubject("Confirm account!");

            String emailContent = "<html><body>"
                    + "<h2>Current Conditions 🌡️</h2>"
                    + "<p>Location: " + weather.getCity() + ", " + weather.getCountry() + "</p>"
                    + "<p>Weather: " + weather.getWeatherType() + "</p>"
                    + "<p>Description: " + weather.getWeatherTypeDescription() + "</p>"
                    + "<p>Temperature: " + weather.getTemperature() + " °C " + (weather.getTemperature() < 5 ? "❄️" : (weather.getTemperature() > 25 ? "🌞" : "")) + "</p>"
                    + "<p>Feels Like: " + weather.getFeelsTemp() + " °C</p>"
                    + "<p>Lowest Temperature: " + weather.getMinTemp() + " °C</p>"
                    + "<p>Highest Temperature: " + weather.getMaxTemp() + " °C</p>"
                    + "<p>Pressure: " + weather.getPressure() + " hPa</p>"
                    + "<p>Humidity: " + weather.getHumidity() + " % 💧</p>"
                    + "<p>Wind Speed: " + weather.getWindSpeed() + " m/s 🌬️</p>"
                    + "<p>Visibility: " + weather.getVisibility() + (weather.getVisibility().equals("High") ? " 👓" : (weather.getVisibility().equals("Low") ? " 🌫️" : "")) + "</p>"
                    + "<p>Sunrise: " + weather.getSunrise() + " 🌅</p>"
                    + "<p>Sunset: " + weather.getSunset() + " 🌇</p>"
                    + "</body></html>";


            helper.setText(emailContent, true);

            javaMailSender.send(message);
            log.info("Email sent: " + email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "thirdStepQueue")
    public void sendSubscriptionMail(SubscribeRequestDto subscribeRequestDto) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");

        try {
            helper.setTo(subscribeRequestDto.getEmail());
            helper.setSubject("🎉 Subscription Successful! 🎉");

            String emailContent = "<html><body>"
                    + "<h1>🎉 Subscription Successful! 🎉</h1>"
                    + "<p>Hello " + subscribeRequestDto.getEmail() + " 👋,</p>"
                    + "<p>Your subscription has been successfully completed. You will now receive daily weather updates for " + subscribeRequestDto.getCity() + " ☀️⛅🌧️🌦️</p>"
                    + "<p>Your payment has been received, and your subscription period has started.</p>"
                    + "<p>Thank you! 🙌</p>"
                    + "</body></html>";

            helper.setText(emailContent, true);

            javaMailSender.send(message);
            log.info("Email sent: " + subscribeRequestDto.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
