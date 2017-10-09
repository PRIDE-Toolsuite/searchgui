package eu.isas.searchgui.cmd;

import com.compomics.software.settings.UtilitiesPathParameters;
import eu.isas.searchgui.parameters.SearchGUIPathParameters;
import java.io.File;
import java.util.HashMap;
import org.apache.commons.cli.CommandLine;

/**
 * Parses the command line and retrieves the user input.
 *
 * @author Marc Vaudel
 */
public class PathSettingsCLIInputBean {

    /**
     * The path set to the temp folder.
     */
    private String tempFolder = "";
    /**
     * The specific paths sets for every option.
     */
    private HashMap<String, String> paths = new HashMap<>();
    /**
     * The folder where to save the logs.
     */
    private File logFolder = null;

    /**
     * Construct a FollowUpCLIInputBean from an Apache CLI instance.
     *
     * @param aLine the command line
     */
    public PathSettingsCLIInputBean(CommandLine aLine) {
        
        if (aLine.hasOption(PathSettingsCLIParams.LOG.id)) {
            logFolder = new File(aLine.getOptionValue(PathSettingsCLIParams.LOG.id));
        }

        if (aLine.hasOption(PathSettingsCLIParams.ALL.id)) {
            tempFolder = aLine.getOptionValue(PathSettingsCLIParams.ALL.id);
        }

        for (SearchGUIPathParameters.SearchGUIPathKey searchGUIPathKey : SearchGUIPathParameters.SearchGUIPathKey.values()) {
            String id = searchGUIPathKey.getId();
            if (aLine.hasOption(id)) {
                paths.put(id, aLine.getOptionValue(id));
            }
        }
        for (UtilitiesPathParameters.UtilitiesPathKey utilitiesPathKey : UtilitiesPathParameters.UtilitiesPathKey.values()) {
            String id = utilitiesPathKey.getId();
            if (aLine.hasOption(id)) {
                paths.put(id, aLine.getOptionValue(id));
            }
        }
    }

    /**
     * Returns the temp folder, an empty string if not set.
     *
     * @return the temp folder
     */
    public String getTempFolder() {
        return tempFolder;
    }

    /**
     * Returns the specific paths provided by the user in a map: Path id &gt;
     * path.
     *
     * @return the specific paths provided by the user
     */
    public HashMap<String, String> getPaths() {
        return paths;
    }

    /**
     * Indicates whether the user gave some path configuration input.
     *
     * @return a boolean indicating whether the user gave some path
     * configuration input.
     */
    public boolean hasInput() {
        return !tempFolder.equals("") || !paths.isEmpty();
    }

    /**
     * Returns the folder where to save the log files.
     * 
     * @return the folder where to save the log files
     */
    public File getLogFolder() {
        return logFolder;
    }
}
