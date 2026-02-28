/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package core;

import java.util.HashMap;

/**
 *
 * @author rash4
 */
public final class CommandTree implements Com{ 
    public CommandTree(){this.CMDS = new HashMap<>();}
    public CommandTree setCom(String name, Com com){
        if(name == null || com == null){}
        else if(com instanceof Command single){
            if(single.com() == null)return this;
            this.CMDS.putIfAbsent(name, single);
        }else if(com instanceof CommandTree tree){
            if(tree.CMDS.isEmpty())return this;
            this.CMDS.putIfAbsent(name, tree);
        }
        return this;
    }
    public void listHelp(String prefix) {
        final var cons = General.console();
        // List the direct command for this SubCommand if it exists
        if (this.subCommand != null) {
            cons.appendl(prefix + "Direct command: " + this.subCommand.getClass().getSimpleName());
        }

        // List subcommands within this SubCommand
        for (String key : this.CMDS.keySet()) {
            Com com = this.CMDS.get(key);
            switch (com) {
                case CommandTree tree -> {
                    // If it's a SubCommand, recursively list its help
                    cons.appendl(prefix + "--" + key + " (SubCommand):");
                    tree.listHelp(prefix + "  ");
                }
                case Command cmd -> // If it's a Command, just print it out
                    cons.appendl(prefix + "--" + key + " (Command)");
            }
        }
    }
    public boolean hasDirectCom(){return this.subCommand != null;}
    @Override public boolean hasParam(){return true;}
    public CommandTree setDirectCom(Command com){
        this.subCommand = com;
        return this;
    }
    public String[] getList(){return this.CMDS.keySet().toArray(String[]::new);}
    public Com getCom(String command){return this.CMDS.get(command);}
    public Command directCommand(){return this.subCommand;}
    private Command subCommand;
    private final HashMap<String, Com> CMDS;
}