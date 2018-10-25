package com.eqy.mail;

class Ticket implements Runnable //extends Thread
{
    Object obj=new Object();
    private static int tick=100;
    public void run()              /*不能在这写同步函数，否则其它线程用不了，需要单独写一个同步函数*/
    {
        while(true)
        {   synchronized(obj)
            {
                if(tick>0)
                { 
                    try
                    {
                        Thread.sleep(10);   /*每次运行就沉睡一下*/
                    }
                    catch(Exception e)
                    {
                        
                    }
                    System.out.println(Thread.currentThread().getName()+"sale:--"+tick--);
                }
            }
          }
    }
}