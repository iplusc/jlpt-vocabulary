//
//  ICMenuViewController.m
//  jlpt_voc
//
//  Created by guo on 2014/07/15.
//  Copyright (c) 2014年 iplus. All rights reserved.
//

#import "ICMenuViewController.h"
#import "ICMainViewController.h"
#import "GADBannerView.h"

@interface ICMenuViewController ()

@end

@implementation ICMenuViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view.
    //UIAlertView *helloWorld = [[UIAlertView alloc]initWithTitle:@"JLPT_VOC" message:@"hello world!!!" delegate:nil cancelButtonTitle:@"OK" otherButtonTitles: nil];
    //[helloWorld show];
    
    [self setLayout];
}

- (void)setLayout
{
    CGRect frame = self.view.frame;
    
    //N1 Button
    UIImage *button_n1 = [UIImage imageNamed: @"button_n1"];
    UIButton *n1Button = [UIButton buttonWithType:UIButtonTypeCustom];
    n1Button.frame = CGRectMake( (frame.size.width - button_n1.size.width)/2, (frame.size.height - button_n1.size.height)/2 -130, button_n1.size.width, button_n1.size.height );
    [n1Button setImage:button_n1 forState:UIControlStateNormal];
    [n1Button addTarget:self action:@selector(showMainPage) forControlEvents:UIControlEventTouchDown];
    
    //N2 Button
    UIImage *button_n2 = [UIImage imageNamed: @"button_n2"];
    UIButton *n2Button = [UIButton buttonWithType:UIButtonTypeCustom];
    n2Button.frame = CGRectMake( (frame.size.width - button_n2.size.width)/2, (frame.size.height - button_n2.size.height)/2 -55, button_n2.size.width, button_n2.size.height );
    [n2Button setImage:button_n2 forState:UIControlStateNormal];
    
    //N3 Button
    UIImage *button_n3 = [UIImage imageNamed: @"button_n3"];
    UIButton *n3Button = [UIButton buttonWithType:UIButtonTypeCustom];
    n3Button.frame = CGRectMake( (frame.size.width - button_n3.size.width)/2, (frame.size.height - button_n3.size.height)/2 +20, button_n3.size.width, button_n3.size.height );
    [n3Button setImage:button_n3 forState:UIControlStateNormal];
    
    //N2 Button
    UIImage *button_n4 = [UIImage imageNamed: @"button_n4"];
    UIButton *n4Button = [UIButton buttonWithType:UIButtonTypeCustom];
    n4Button.frame = CGRectMake( (frame.size.width - button_n4.size.width)/2, (frame.size.height - button_n4.size.height)/2 +105, button_n4.size.width, button_n4.size.height );
    [n4Button setImage:button_n4 forState:UIControlStateNormal];
    
    
    [self.view addSubview:n1Button];
    [self.view addSubview:n2Button];
    [self.view addSubview:n3Button];
    [self.view addSubview:n4Button];
    
    //[self addAdMob];
}

- (void)addAdMob
{
    GADBannerView* bannerView = [[GADBannerView alloc]
                                 initWithFrame:CGRectMake(0.0,self.view.frame.size.height - GAD_SIZE_320x50.height,
                                                          GAD_SIZE_320x50.width,
                                                          GAD_SIZE_320x50.height)];
    NSBundle* bundle = [NSBundle mainBundle];
    //読み込むファイルパスを指定
    NSString* path = [bundle pathForResource:@"AdMob" ofType:@"plist"];
    NSDictionary* dic = [NSDictionary dictionaryWithContentsOfFile:path];
    NSString *unitID = [dic objectForKey:@"adUnitID"];
    NSLog(@"unitID::: %@",unitID);
    bannerView.adUnitID = unitID;
    
    // ビュー階層に追加する。
    bannerView.rootViewController = self;
    [self.view addSubview:bannerView];
    // 一般的なリクエストを行って広告を読み込む。
    [bannerView loadRequest:[GADRequest request]];
}

- (void)showMainPage
{
    ICMainViewController *mainView = [[ICMainViewController alloc]initWithNibName:@"ICMainViewController" bundle:nil];
    [self presentViewController:mainView animated:YES completion:nil];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender
{
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
