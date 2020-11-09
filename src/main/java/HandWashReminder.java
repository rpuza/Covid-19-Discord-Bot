import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

import java.time.LocalDateTime;

public class HandWashReminder {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        LocalDateTime now = LocalDateTime.now();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();

        if(hour == 8 && minute == 0 && second == 0) {
            e.getChannel().sendMessage("Time to eat breakfast! Remember to wash your hands before eating your food!").queue();
        }

        if(hour == 12 && minute == 0 && second == 0) {
            e.getChannel().sendMessage("Time to eat lunch! Remember to wash your hands before eating your food!").queue();
        }

        if(hour == 18 && minute == 0 && second == 0) {
            e.getChannel().sendMessage("Time to eat dinner! Remember to wash your hands before eating your food!").queue();
        }
    }
}
