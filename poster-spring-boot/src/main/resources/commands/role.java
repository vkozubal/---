package commands;

import org.crsh.cli.Command;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.pti.poster.service.impl.ShellCommandsService;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class role extends BaseCommand{

    @Usage("Maps user count on role")
    @Command
    public Object main() {
        ShellCommandsService service = ((DefaultListableBeanFactory)context.getAttributes().get("spring.beanfactory")).getBean(ShellCommandsService.class);
        return service.getStatByRoles();
    }
}
