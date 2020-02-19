package xyz.chph.toy.compiler;

import com.alibaba.fastjson.JSON;

import java.io.*;
import java.util.List;

/*
 * Created by steve_chph, 2020/2/18 下午3:22
 */

public class ToyMake {

    static class Script {
        private List<String> before;
        private List<String> after;

        public Script() {
        }

        public List<String> getBefore() {
            return before;
        }

        public void setBefore(List<String> before) {
            this.before = before;
        }

        public List<String> getAfter() {
            return after;
        }

        public void setAfter(List<String> after) {
            this.after = after;
        }
    }

    static class Module {
        private String module;
        private List<String> pipeline;
        private List<String> others;
        private Script scripts;

        public Module() {
        }

        public String getModule() {
            return module;
        }

        public void setModule(String module) {
            this.module = module;
        }

        public List<String> getPipeline() {
            return pipeline;
        }

        public void setPipeline(List<String> pipeline) {
            this.pipeline = pipeline;
        }

        public List<String> getOthers() {
            return others;
        }

        public void setOthers(List<String> others) {
            this.others = others;
        }

        public Script getScripts() {
            return scripts;
        }

        public void setScripts(Script scripts) {
            this.scripts = scripts;
        }
    }

    static class Configuration {
        private String project;
        private List<Module> modules;

        public Configuration() {
        }

        public String getProject() {
            return project;
        }

        public void setProject(String project) {
            this.project = project;
        }

        public List<Module> getModules() {
            return modules;
        }

        public void setModules(List<Module> modules) {
            this.modules = modules;
        }
    }

    public Configuration getProjectConfiguration(String src) {
        String json = loadToyMake(src);
        return JSON.parseObject(json, Configuration.class);
    }

    private String loadToyMake(String toyMake) {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bw = new BufferedReader(new InputStreamReader(new FileInputStream(toyMake)));
            bw.lines().forEach(sb::append);
        } catch (FileNotFoundException e) {
            System.err.println("something wrong when loading ToyMake.json!");
            System.exit(1);
        }
        return sb.toString();
    }
}
