//
//  ICMainViewController.m
//  jlpt_voc
//
//  Created by guo on 2014/07/15.
//  Copyright (c) 2014年 iplus. All rights reserved.
//

#import "ICMainViewController.h"
#import "ICDeviceHelper.h"
#import <QuartzCore/QuartzCore.h>

@interface ICMainViewController ()

@end

@implementation ICMainViewController
@synthesize numberLabel;
@synthesize titleLabel;
@synthesize kanaLabel;
@synthesize sampleStr;
@synthesize sampleLabel;

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
    [self setLayout];
}

- (void)setLayout
{
    // Do any additional setup after loading the view from its nib.
    
    if([ICDeviceHelper is568h] == YES){
        
    } else {
        //TODO: resize
    }
    sampleStr.layer.borderColor = [UIColor cyanColor].CGColor;
    sampleStr.layer.borderWidth = 0.8;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
