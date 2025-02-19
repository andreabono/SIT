
package org.ingv.sit.utils;

import javafx.concurrent.Task;

/**
 *
 * @author andreabono
 */
public class MyTask extends Task<Void> {
        enum TaskType {
            TYPE1, TYPE2, TYPE3;
        }

    
        private TaskType type;

        public MyTask(String title) {
            updateTitle(title);

            type = TaskType.values()[(int) (Math.random() * 3)];
        }

        public TaskType getType() {
            return type;
        }

        @Override
        protected Void call() throws Exception {

            if (Math.random() < .3) {
                updateMessage("First we sleep ....");
                Thread.sleep(2500);
            }

            int max = 100000;
            for (int i = 0; i < max; i++) {
                if (isCancelled()) {
                    break;
                }
                
                //System.out.println("Message " + i);
                updateMessage("Message " + i);
                updateProgress(i, max);
            }

            updateProgress(0, 0);
            done();
            return null;
        }
    }
