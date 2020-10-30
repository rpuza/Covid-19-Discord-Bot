import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DetectOnOff extends ListenerAdapter {
    public static boolean filterOn = true;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        if (event.getMessage().getContentRaw().equalsIgnoreCase("~detectfakenews") && filterOn){
            filterOn = false;
            event.getChannel().sendMessage("The Fake News Detector has been disabled by " + event.getMember().getUser().getName()).queue();
        }else if(event.getMessage().getContentRaw().equalsIgnoreCase("~detectfakenews") && !filterOn){
            filterOn = true;
            event.getChannel().sendMessage("The Fake News Detector has been enabled by " + event.getMember().getUser().getName()).queue();
        }
    }
}
