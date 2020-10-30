import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Detect {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if(DetectOnOff.filterOn){
            String[] LIST_OF_MYTHS = {"bleach", "hoax", "fake pandemic", "microchips", "just a flu", "only elderly", "only people with preconditions", "vaccine is available"};
            String[] message = e.getMessage().getContentRaw().split(" ");
            for(int i = 0;i < message.length;i++){
                boolean myth = false;
                //Check each message for each myths
                for(int b = 0; b < LIST_OF_MYTHS.length;b++){
                    if (message[i].equalsIgnoreCase(LIST_OF_MYTHS[b])){
                        e.getMessage().delete().queue();
                        myth = true;
                        if(DetectMessage.allowed){ //Prints a message IF enabled by ~detectfakenews
                            e.getChannel().sendMessage("Don't get fooled by a false information! " + e.getMember().getUser().getName()).queue();
                        }
                    }
                }
                System.out.println(message[i] + " " + myth); //Just a report for console
            }
        }
    }
}
