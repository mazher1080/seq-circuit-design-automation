package state_table_solver.VHDLGeneration;

import java.util.ArrayList;
import java.util.List;

public class VHDLConditional {
    private List<VHDLSignal> highConditions;
    private List<VHDLSignal> lowConditions;

    public VHDLConditional(VHDLSignal signal) {
        this.highConditions = new ArrayList<VHDLSignal>();
        this.lowConditions = new ArrayList<VHDLSignal>();
    }

    public void addHighCondition(VHDLSignal highSig) {
        this.highConditions.add(highSig);
    }

    public void addLowCondition(VHDLSignal lowSig) {
        this.lowConditions.add(lowSig);
    }

    public String toVHDLConditionString() {
        String output = "(";
        for(int i = 0; i < this.highConditions.size(); i++) {
            if(i != 0) {
                output += " or";
            }
            output += "(" + this.highConditions.get(i).getId()) + " = '1'" + ")";
        }
        for(int j = 0; j < this.highConditions.size(); j++) {
            if(j != 0) {
                output += " or";
            }
            output += "(" + this.highConditions.get(j).getId() + " = '0'" + ")";
        }
        output += ")";

        return output;
    }

    public String getCurrent
}
