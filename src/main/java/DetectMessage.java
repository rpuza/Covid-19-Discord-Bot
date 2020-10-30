import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class DetectMessage extends ListenerAdapter {
    public static boolean allowed = false;

    public void onGuildMessageReceived(GuildMessageReceivedEvent e){
        if(DetectOnOff.filterOn) {
            if (e.getMessage().getContentRaw().equalsIgnoreCase("~detectfakenews") && !allowed) {
                e.getChannel().sendMessage("Detecting fake news has been enabled.").queue();
                System.out.println("Enabled");
                allowed = true;
            } else if (e.getMessage().getContentRaw().equalsIgnoreCase("~detectfakenews") && allowed) {
                e.getChannel().sendMessage("Detecting fake news has been disabled.").queue();
                System.out.println("Disabled");
                allowed = false;
            }
        }else if(e.getMessage().getContentRaw().equalsIgnoreCase("~detectfakenews") && !DetectOnOff.filterOn){
            e.getChannel().sendMessage("You can't detect fake news while the filter is off. To turn the filter on, run ~detectfakenews").queue();
        }


    }
}
