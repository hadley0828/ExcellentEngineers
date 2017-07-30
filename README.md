# 机器学习


迭代一模型是一个实现了中英翻译的s2s模型，配置环境为python3.6,安装tensorflow、jieba、deepnlp第三方库，运行方式如下

训练模式：在命令行中输入指令 python translate.py --train --data_dir '' --train_dir '' --from_vocab_size=50000 --to_vocab_size=50000 --from_train_data '' --to_train_data '' --from_dev_data '' --to_dev_data '' --size=256 --num_layers=2 --steps_per_checkpoint=100 --max_train_data_size=0
		 其中translate.py文件为 src/main/python/ite1/ 目录下的文件	
		 data_dir参数为语料库目录，train_dir参数为训练结果目录，from_train_data参数为part.zh目录，to_train_data参数为part.en目录，from_dev_data参数为dev.zh目录，to_dev_data参数为dev.en目录
		 训练参数为size，num_layers,step_per_checkpoint,max_train_data_size

翻译模式：在命令行中输入指令 python translate.py --decode --data_dir '' --train_dir '' --from_vocab_size=50000 --to_vocab_size=50000 --size=256 --num_layers=2 
		 其中所有参数需保证和训练模式中相同
  
  
 



迭代二模型是一个实现了对评价解析评分的rnn模型，配置环境为python3.6，安装tensorflow，deepnlp第三方库，运行方式如下
lstm.py文件在src/main/python目录下，运行前先设置参数

vocabulary_Path：词典路径
PKL_PATH：amazom.pkl 路径
MODEL_PATH：模型存储路径

训练模式： 修改lstm.py最下方指令为train()后在命令行中输入指令 python lstm.py 
评分模式： 修改lstm.py最下方指令为decode()后在命令行中输入指令 python lstm.py 

在web下运行需已经拥有两个模型后同时在命令行中开启两个模型的decode()模式，然后运行src/main/java/runner/Runner.java,在浏览器中输入localhost:8080

