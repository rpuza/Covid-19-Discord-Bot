import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;

public class Test {
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        DetectMessage dm = new DetectMessage();
        if(DetectMessage.allowed) {
            dm.onGuildMessageReceived(e);
            if(DetectMessage.allowed == false) {
                System.out.println("Yay 1");
            }
        } else {
            dm.onGuildMessageReceived(e);
            if(DetectMessage.allowed == true) {
                System.out.println("Yay 2");
            }
        }

        DetectOnOff doo = new DetectOnOff();
        if(DetectOnOff.filterOn) {
            doo.onGuildMessageReceived(e);
            if(DetectOnOff.filterOn == false) {
                System.out.println("Yay 3");
            }
        } else {
            doo.onGuildMessageReceived(e);
            if(DetectOnOff.filterOn == true) {
                System.out.println("Yay 4");
            }
        }

        Quarantine q = new Quarantine();
        if(q.activities.size() == q.activitiesNum.size()) {
            System.out.println("Yay 5");
        }

    }
}
